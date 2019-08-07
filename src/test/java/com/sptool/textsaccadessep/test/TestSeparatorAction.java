package com.sptool.textsaccadessep.test;

import com.sptool.textsaccadessep.web.SeparatorAction;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsTestCase;

public class TestSeparatorAction extends StrutsTestCase {

    public void testEmptyForm() throws  Exception {
        ActionProxy proxy = getActionProxy("/separatorAction");

        SeparatorAction sepAction = (SeparatorAction) proxy.getAction();

        proxy.execute();

        assertTrue("Problem There were no errors present in fieldErrors but there should have been one error present", sepAction.getActionErrors().size() >= 1);
    }

    public void testForm() throws  Exception {
        request.setParameter("saccadesNbr", "3");
        request.setParameter("text", "this is a text.\r\n Of two lines.");

        ActionProxy proxy = getActionProxy("/separatorAction");

        SeparatorAction sepAction = (SeparatorAction) proxy.getAction();

        proxy.execute();

        assertTrue("Problem Wrong Result For One Line Input ", sepAction.getLignes().size() == 2);
    }
}
