package com.atlwork.angularseed.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atlwork.angularseed.app.domain.Mission;
import com.atlwork.angularseed.app.service.MissionService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

@RestController
public class GenerateMissionPdf {

    @Autowired
    private MissionService missionService;

    @RequestMapping(method = RequestMethod.GET, value = "/api/mission-report/{idMission}", produces = "application/pdf")
    public void missionToPdf(@PathVariable Long idMission, HttpServletResponse response, HttpServletRequest request) throws Exception {

	Mission mission = missionService.read(idMission);

	response.setContentType("application/pdf");
	response.setHeader("Content-Disposition", "attachment; filename=mission" + idMission + ".pdf");

	Document document = new Document(PageSize.A4, 20, 20, 50, 25);
	PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
	HeaderFooterPageEvent event = new HeaderFooterPageEvent();
	writer.setPageEvent(event);

	document.open();

	document.addTitle(mission.getTitle());
	document.addSubject("Fiche Projet");
	document.addKeywords("Report, PDF, iText");
	document.addAuthor("atlwork ficheprojet");
	document.addCreator("atlwork ficheprojet");

	Paragraph title = new Paragraph(mission.getTitle(), FontFactory.getFont("Times-Roman", 12, Font.BOLD));
	title.setAlignment(Element.ALIGN_CENTER);

	document.add(title);

	document.add(new Paragraph(" "));
	document.add(new Paragraph(" "));

	PdfPTable tableTop = new PdfPTable(3);

	BaseColor headerColor = new BaseColor(255, 0, 0);

	tableTop.addCell(buildPdfPCell("Client", headerColor));
	tableTop.addCell(buildPdfPCell("Project", headerColor));
	tableTop.addCell(buildPdfPCell("Function", headerColor));

	tableTop.addCell(mission.getClient().getAccount());
	tableTop.addCell(mission.getProject().getName());
	tableTop.addCell(mission.getFunction().getName());

	tableTop.addCell(buildPdfPCell("Function Type", headerColor));
	tableTop.addCell(buildPdfPCell("Mission Type", headerColor));
	tableTop.addCell(buildPdfPCell("", BaseColor.WHITE));

	tableTop.addCell(mission.getFunctionType().getName());
	tableTop.addCell(mission.getMissionType().getName());

	tableTop.addCell(buildPdfPCell("", BaseColor.WHITE));
	tableTop.addCell(buildPdfPCell("", BaseColor.WHITE));
	tableTop.addCell(buildPdfPCell("", BaseColor.WHITE));

	document.add(tableTop);

	document.add(new Paragraph(" "));

	PdfPTable tableButtom = new PdfPTable(2);

	tableButtom.addCell(buildPdfPCell("Technical Skills :", BaseColor.GRAY));
	tableButtom.addCell(buildPdfPCell(mission.getTechnicalSkills(), BaseColor.WHITE));
	tableButtom.addCell(buildPdfPCell("Functional Skills :", BaseColor.GRAY));
	tableButtom.addCell(buildPdfPCell(mission.getFunctionalSkills(), BaseColor.WHITE));

	tableButtom.addCell(buildPdfPCell("Context :", BaseColor.GRAY));
	tableButtom.addCell(buildPdfPCell(mission.getContext(), BaseColor.WHITE));
	tableButtom.addCell(buildPdfPCell("Approache :", BaseColor.GRAY));
	tableButtom.addCell(buildPdfPCell(mission.getApproche(), BaseColor.WHITE));
	tableButtom.addCell(buildPdfPCell("Methododology :", BaseColor.GRAY));
	tableButtom.addCell(buildPdfPCell(mission.getMethodology(), BaseColor.WHITE));
	tableButtom.addCell(buildPdfPCell("Benefits :", BaseColor.GRAY));
	tableButtom.addCell(buildPdfPCell(mission.getBenefits(), BaseColor.WHITE));

	document.add(tableButtom);
	document.close();
    }

    private PdfPCell buildPdfPCell(String value, BaseColor color) {
	PdfPCell pdfCell = new PdfPCell(new Phrase(value));
	pdfCell.setBackgroundColor(color);
	return pdfCell;
    }

    private static class HeaderFooterPageEvent extends PdfPageEventHelper {

	Font font = new Font(FontFamily.TIMES_ROMAN, 7.0f, Font.UNDERLINE, BaseColor.RED);

	@Override
	public void onStartPage(PdfWriter writer, Document document) {
	    ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Copyright © 2015 - All Rights Reserved - ATLWORK", font),
		    110, 800, 0);
	}

	@Override
	public void onEndPage(PdfWriter writer, Document document) {
	    ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Copyright © 2015 - All Rights Reserved - ATLWORK", font),
		    110, 30, 0);
	}

    }
}
