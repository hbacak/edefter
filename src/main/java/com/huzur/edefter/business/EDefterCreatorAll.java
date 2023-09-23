package com.huzur.edefter.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;

import com.huzur.edefter.model.EDefterAbstract;
import com.huzur.edefter.model.EDefterBaslik;
import com.huzur.edefter.model.EDefterDetay;
import com.huzur.edefter.model.EDefterFactory;
import com.huzur.utils.excel.Excel;

public class EDefterCreatorAll {
	private static final int EXCEL_MAX_ROW = 65534;
	
	private static List<EDefterBaslik> edefterList = new ArrayList<>();
	public static void main(String[] args){
		String[] fileExtensions = {"csv"};
		String fileName = args.length > 0 ? args[0] : "./edefter";
		File directory = new File(fileName);
		for (Iterator<File> files = FileUtils.iterateFiles(directory, fileExtensions, false); files.hasNext();) {
			File file = files.next();
			System.out.println(file.getName() + " olusturuluyor");
			createEdefter(file);
		}
		try {
			createExcel(edefterList, args[0], "Edefter");
		} catch (IOException e) {
			System.err.println("Okuma/Yazma hatasi");
			e.printStackTrace();
		}
		System.out.println("İşlemler bitmiştir");
	}

	public static void createEdefter(File sourceFile) {
		try {
			// csv dosyasını oku
			EDefterAbstract oncekiSatir = null;
			EDefterBaslik baslik = null;
			CSVParser parser = getCSVParser(sourceFile.getAbsolutePath());
			for (CSVRecord csvRecord : parser){
				EDefterAbstract satir = EDefterFactory.getEDefter(csvRecord); 
				if(satir == null) {
					System.err.println(csvRecord);
				}
				// her satır için krediliSatislar nesnesi oluştur
				if(oncekiSatir == null || !oncekiSatir.isAyniFis(satir)){
					if(baslik != null) {
						edefterList.add(baslik);
					}
					oncekiSatir = satir;
					baslik = satir.toEDefterBaslik();
				} 
				for (EDefterDetay detay : satir.toEDefterDetay()) {
					baslik.ekleDetay(detay);
				}
			}
			edefterList.add(baslik);
			//createCSV(edefterList);
			//String newFileName = FilenameUtils.removeExtension(sourceFile.getName());
			//createExcel(edefterList, newFileName);
		} catch (ArrayIndexOutOfBoundsException aiobe) {
			System.err.println("Dosya veya tip belirtilmemis veya fazla kolona erişmeye çalışıldı");
			aiobe.printStackTrace();
		} catch (FileNotFoundException fnfe) {
			System.err.println("Dosya bulunamiyor. " + sourceFile.getName());
		} catch (IOException ioe){
			System.err.println("Okuma/Yazma hatasi");
		}
	}

	private static CSVParser getCSVParser(String fileName) throws IOException {
		File edefterAbstractFile = new File(fileName);
		return CSVParser.parse(
				edefterAbstractFile, 
				Charset.forName("UTF-8"), 
				CSVFormat.EXCEL
				.withDelimiter(';')
				.withHeader()
				.withSkipHeaderRecord());
	}
	
	private static void createExcel(List<EDefterBaslik> edefterList, String path, String fileName) throws IOException{
		Excel outExcel = new Excel();
		int baslikSheetSayisi = bulSheetAdedi(edefterList.size());
		System.out.println("baslik kısmı ekleniyor");
		for (int i = 0; i < baslikSheetSayisi; i++) {
			outExcel.addSheet(bulSubList(edefterList, i), "Baslik" + (i+1));
		}
		
		List<EDefterDetay> detayList = new ArrayList<>();
		for (EDefterBaslik eDefterBaslik : edefterList) {
			detayList.addAll(eDefterBaslik.getDetayList());
		}
		int detaySheetSayisi = bulSheetAdedi(detayList.size());
		for (int i = 0; i < detaySheetSayisi; i++) {
			System.out.println("detay" + (i+1) + " kısmı ekleniyor");
			outExcel.addSheet(bulSubList(detayList, i), "Detay" + (i+1));
		}
		System.out.println("dosyaya yazılıyor");
		outExcel.writeFile(path, fileName);
	}
	
	private static int bulSheetAdedi(int size) {
		int sheetAdedi = size / EXCEL_MAX_ROW;
		if(size % EXCEL_MAX_ROW > 0) {
			sheetAdedi++;
		}
		return sheetAdedi;
	}
	
	private static List<? extends Object> bulSubList(List<? extends Object> list, int sheetNo) {
		return list.subList(EXCEL_MAX_ROW * sheetNo, Math.min(list.size(), EXCEL_MAX_ROW * (sheetNo + 1)));
	}
}
