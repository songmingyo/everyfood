package com.doppio.common.service.impl;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.tronic.util.DateUtil;
import org.springframework.util.ObjectUtils;

import com.doppio.common.model.AttachFileVo;
import com.doppio.common.service.ImageConvertPdfService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

@Service("imageConvertPdfService")
public class ImageConvertPdfServiceImpl implements ImageConvertPdfService {

	private static final Logger logger = LoggerFactory.getLogger(ImageConvertPdfServiceImpl.class);

	@Autowired
	FileManagerMapper fileManagerMapper;

	@Value("#{config['File.Sys.Path']}") // 웹루트 경로 (웹서비스용 파일업로드 패스)
	public String FileSysPath;

	@Value("#{config['File.StorePath.AppService.Smp']}") // 웹루트 경로 (웹서비스용 파일업로드 패스)
	public String FileTaskPath;

	@Autowired
	private ImageLoadMapper imageLoadMapper;

	/**
	 * @Method : convertImageTopdf
	 * @Description : Convert Images to Pdf
	 * @param pMap
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String convertImageTopdf(Map<String, Object> pMap) throws Exception {
		String result = null;

		if (!ObjectUtils.isEmpty(pMap)) {
			AttachFileVo paramVo = new AttachFileVo();
			paramVo.setAtchFileId(String.valueOf(pMap.get("AtchFileId")));

			List<AttachFileVo> fileVoList = imageLoadMapper.selectCommonFileInfoList(paramVo);
			if (CollectionUtils.isNotEmpty(fileVoList)) {

				Document document = null;
				FileOutputStream fout = null;
				String atchFileId = DateUtil.getTimeStamp();

				if (!ObjectUtils.isEmpty(atchFileId)) {

					try {
						document = new Document();

						fout = new FileOutputStream(FileSysPath + FileTaskPath + atchFileId + ".pdf");
						PdfWriter.getInstance(document, fout);

						document.open();
						Rectangle a = document.getPageSize();

						Image img = null;
						for (AttachFileVo vo : fileVoList) {
							img = Image.getInstance(FileSysPath + FileTaskPath + vo.getSaveFileNm());
							img.scaleToFit(a);
							img.setAbsolutePosition(0, 0);
							document.add(img);
							document.newPage();
						}

						AttachFileVo attachFileVO = new AttachFileVo();
						attachFileVO.setAtchFileId(atchFileId);
						attachFileVO.setSeq("1");
						attachFileVO.setOrgFileNm(atchFileId + ".pdf");
						attachFileVO.setSaveFileNm(atchFileId + ".pdf");
						attachFileVO.setFileKindCd("ASMP");
						attachFileVO.setFilePathNm("smp/");
						attachFileVO.setFileTypeNm("pdf");
						fileManagerMapper.insertFileData(attachFileVO);

						result = attachFileVO.getAtchFileId();

					} catch (Exception e) {
						logger.debug(e.getMessage());
					} finally {
						if (fout != null) {try {fout.close();} catch (Exception e) {logger.debug(e.getMessage());}}
						if (document != null) {try {document.close();} catch (Exception e) {logger.debug(e.getMessage());}}
					}

				}
			}
		}

		return result;
	}

}
