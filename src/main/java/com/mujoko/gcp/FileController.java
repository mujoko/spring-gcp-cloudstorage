package com.mujoko.gcp;

import java.io.IOException;
import java.io.OutputStream;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.storage.GoogleStorageResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.Storage;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/v3")
public class FileController   {

	Logger logger = LoggerFactory.getLogger(FileController.class);
	 
	private Resource gcsFile;
	
	@Autowired
	AppUtil appUtil;
	
	@Autowired
	private Storage storage;
  

 
	@ResponseStatus(HttpStatus.OK)
	@Operation( 
			  summary = "Upload File ", description = "Upload Profile ", tags = {
	"uploadProfile" })
	@RequestMapping(path = "/uploadFile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void uploadProfil (
			@Valid @RequestPart(value = "user_photo", required = true) MultipartFile file, @RequestParam(value="fileName", required = true) String fileName) throws IOException
					  {
		logger.info(" upload   ");
		writeFile(file.getBytes(), fileName );
	}

	
	private void writeFile(byte[] file, String fileName ) throws IOException {
		byte[] bytes = file; 
		gcsFile=new GoogleStorageResource(storage,"gs://"+appUtil.getImageStorageBucket()+"/" +fileName);
		try (OutputStream os = ((WritableResource) this.gcsFile).getOutputStream()) {
			os.write(bytes);
		}
		 
	}
 
}
