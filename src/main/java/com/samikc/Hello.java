package com.samikc;

import com.samikc.hello.gen.schema.bean.Beans;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringWriter;

/**
 * Created by samikc on 4/11/15.
 */
public class Hello {

    public static void main(String[] args) throws Exception{

        File file = new File("beans.xml");
        JAXBContext ctx = JAXBContext.newInstance(Beans.class);
        Unmarshaller unmarshaller = ctx.createUnmarshaller();
        Beans b = (Beans)unmarshaller.unmarshal(file);
        System.out.println(b.getBean().getName());

        /*  first, get and initialize an engine  */
        VelocityEngine ve = new VelocityEngine();
        ve.init();
        /*  next, get the Template  */
        Template t = ve.getTemplate( "bean-class.vm" );
        /*  create a context and add data */
        VelocityContext context = new VelocityContext();
        context.put("className",b.getBean().getName());
        context.put("fields", b.getBean().getFields().getField());
        /* now render the template into a StringWriter */
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        /* show the World */
        System.out.println(writer.toString());


    }
}
