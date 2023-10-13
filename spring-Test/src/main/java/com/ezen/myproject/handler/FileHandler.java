package com.ezen.myproject.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.myproject.domain.fileVO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

//component:사용자가만듬 <-> bean(라이브러리)

@AllArgsConstructor
@Component
@Slf4j
public class FileHandler 
{
	private final String UP_DIR="D:\\_mywebspring\\_java\\fileupload"; //내 파일 기본 경로
	
	public List<fileVO> uploadFiles(MultipartFile[] files)
	{
		//멀티파트 파일 객체를 받아서 fileVO 형태로 저장한 후
		//오늘 날짜 경로(가변형태로 생성) /실제 파일을 해당 경로에 저장
		//fileVO를 List에 추가 => return list;0
		
		//오늘날짜 경로 생성
		LocalDate date=LocalDate.now();
		log.info(">>date : "+date);
		String today=date.toString();	//date는 객체이므로 2023-10-23 string으로 변환
		//2023\\10\\13 String 생성
		today=today.replace("-",File.separator);
		
		//오늘날짜(today)의 폴더 구성
		File folders=new File(UP_DIR,today);
		if(!folders.exists())//folder가 있는지 없는지 판별
			folders.mkdirs();	//폴더 여러개 생성 명령
		
		//리스트
		List<fileVO> flist=new ArrayList<fileVO>();
		
		//여러개의 파일들 중 순서대로 하나의 파일 가져오기
		for(MultipartFile file: files) 
		{
			fileVO fvo= new fileVO();
			fvo.setSave_dir(today); //공통 뒷쪽 오늘날짜 경로만 set
			fvo.setFile_size(file.getSize()); //getSize()->return타입 long
			
			//파일 이름 설정(OriginalFilenam()설정)
			//OriginalFilenam : 파일 경로를 포함하고 있을 수 있음.
			log.info(">> getName : "+file.getName());	//파일 객체의 종류
			log.info(">>original name : "+file.getOriginalFilename());	//파일 이름
			String originalFileName=file.getOriginalFilename();
			String onlyFileName=originalFileName.substring(originalFileName.lastIndexOf(File.separator)+1);	//혹시라도 경로를 포함하고 있을수도 있어서 파일이름만 떼준다
			
			log.info(">>onlyFileName>>"+onlyFileName);
			fvo.setFile_name(onlyFileName);  //파일 이름 설정
			
			//UUID 생성
			UUID uuid=UUID.randomUUID();
			log.info(">>uuid : "+uuid);
			fvo.setUuid(uuid.toString()); //uuid는 객체이므로 toString
			// --------여기까지 fvo 설정 완료 fvo setting--------------
			
			//디스크에 저장할 파일 객체 생성-> 저장
			//uuid_파일네임
			String fullFileName=uuid.toString()+"_"+onlyFileName;
			File storeFile=new File(folders,fullFileName);
			
			//저장=> 폴더가 없으면 저장이 안되기 때문에 io Exception이 발생하므로 try catch해줘야됨
			try {
				file.transferTo(storeFile);//원본 객체를 저장을 위한 형태로 변경 후 복사
				//파일 타입을 결정 => 이미지 파일이라면 썸네일 생성
				if(isImageFile(storeFile)) {
					fvo.setFile_type(1);
					//uuid_th_파일네임
					File thumbNail=new File(folders,uuid.toString()+"_th_"+onlyFileName);
					Thumbnails.of(storeFile).size(75,75).toFile(thumbNail);
				}
				
			} catch (Exception e) {
				log.info(">>file 생성 오류");
				e.printStackTrace();
			}
			flist.add(fvo);
		}
		
		return flist;
	}
	//tika를 사용하여 파일 형식 체크 -> 이미지 파일이 맞는지 확인
	public boolean isImageFile(File storFile) throws IOException {
		String mimeType = new Tika().detect(storFile);	//image/jpg, image/png
		return mimeType.startsWith("image")? true:false;
	}
}



