import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextRenderInfo;

public class getFont {
    public static void main(String[] args) throws IOException {
        String pdfPath = "src/resource/ZooKeeper_Wait_free_coordination_for_Internet_scale_systems.pdf";
        File file = new File(pdfPath);
        FileInputStream fileInputStream = new FileInputStream(file);
        // {CreationDate=D:20220516123335+08'00', Producer=iText? 5.5.13.1 ?2000-2019
        // iText Group NV (AGPL-version), ModDate=D:20220516123336+08'00'}
        // 修改完pdf后需要修改下修改和创建时间
        PdfReader pdfReader = new PdfReader(fileInputStream);
        System.out.println(pdfReader.getInfo());

        TextExtractionStrategy textExtractionStrategy = new TextExtractionStrategy() {
            @Override
            public void beginTextBlock() {
                // TODO Auto-generated method stub

            }

            @Override
            public void endTextBlock() {
                // TODO Auto-generated method stub

            }

            @Override
            public String getResultantText() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public void renderImage(ImageRenderInfo renderInfo) {
                // TODO Auto-generated method stub

            }

            @Override
            public void renderText(TextRenderInfo renderInfo) {
                // TODO Auto-generated method stub
                if (renderInfo != null)
                    System.out.println(renderInfo.getFont().getPostscriptFontName());
            }
        };
        System.out.println(PdfTextExtractor.getTextFromPage(pdfReader, 1, textExtractionStrategy));
        
    }
}

class MyTextExtractionStrategy implements TextExtractionStrategy {
    @Override
    public void beginTextBlock() {
        // TODO Auto-generated method stub

    }

    @Override
    public void endTextBlock() {
        // TODO Auto-generated method stub

    }

    @Override
    public String getResultantText() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void renderImage(ImageRenderInfo renderInfo) {
        // TODO Auto-generated method stub

    }

    @Override
    public void renderText(TextRenderInfo renderInfo) {
        // TODO Auto-generated method stub

        System.out.println(renderInfo.getFont().getSubfamily());
    }
}