package com.sptool.textsaccadessep.web;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.lang.String;
import java.util.List;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class SeparatorAction extends ActionSupport
{
    private Integer saccadesNbr;
    private Date nowDate;
    private String text;
    private Graphics g;
    private List<List<String>> lignes = new ArrayList<List<String>>();
    private List<List<ByteBuffer>> lignes2 = new ArrayList<List<ByteBuffer>>();

    private static final Logger LOG = LoggerFactory.getLogger(SeparatorAction.class);

    @Override
    public void validate(){
        if (saccadesNbr == null || saccadesNbr.equals(0) || text==null || text.length()==0) {
            addActionError(getText("error.enter.message"));
            return;
        }

        if(saccadesNbr < 2 || saccadesNbr > 5)
            addActionError(getText("error.enter.correct.number"));

    }

    /*
      Main function
      transformes the text into a List of lines each line is a List of saccades

      the 24 size in the font is linked to the 22 size in the index style
      the 800 is the chosen size of the paragraph in the index style
     */
    @Override
    public String execute() throws Exception {
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
        Font font = new Font("Arial", Font.PLAIN, 24);
        int textwidth = (int)(font.getStringBounds(text, frc).getWidth());

        LOG.info("TestAction textWidth : " + textwidth);

        int textPixels = textwidth;

        int textLength = text.length();
        LOG.info("TestAction textLength : " + textLength);
        int meanCharPixels = textPixels/textLength;
        LOG.info("TestAction meanCharPixels : " + meanCharPixels);
        int saccadeLength = 800/(saccadesNbr*meanCharPixels);
        LOG.info("TestAction saccadeLength : " + saccadeLength);

        lignes.add(new ArrayList<String>());
        String chunk;

        for(int i = 0, y = 0, c = 0; i < textLength; c++){
            y = i + saccadeLength;
            int s = 0;

            // a saccade is interrupted by a new line
            for(s = i; s < text.length() && s < i + saccadeLength && text.charAt(s) != '\n'; s++);
            y = s;

            // a chunks right limit cant be above the text size
            if(y >= textLength)
                y = textLength -1;

            // create a new list for a new line when the previous line took the defined number of saccades
            if(c == saccadesNbr) {
                lignes.add(new ArrayList<String>());
                c = 0;
            }

            int  l = 0;

            // make sure to include a word in the saccade when some of it letters are included
            for(l = y + 1; l < text.length() && text.charAt(l) != ' ' && text.charAt(l - 1) != '\n'; l++);

            chunk = text.substring(i, l);
            chunk = chunk.replace("\r\n", "");

            if(!chunk.isEmpty())

                lignes.get(lignes.size() - 1).add(chunk);

            // create a new list for a new line when a line separator
            if(l - 1 < text.length() && (text.charAt(l - 1) == '\n')){
                lignes.add(new ArrayList<String>());
                c = -1;
            }

            i = l;
        }

        LOG.info("TestAction lignes.size : " + lignes.size());

        return ActionSupport.SUCCESS;
    }

    public Integer getSaccadesNbr() {
        return saccadesNbr;
    }

    public void setSaccadesNbr(Integer saccadesNbr) {
        this.saccadesNbr = saccadesNbr;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getNowDate() {
        return nowDate;
    }

    public List<List<String>> getLignes() {
        return lignes;
    }

    public void setLignes(List<List<String>> lignes) {
        this.lignes = lignes;
    }
}
