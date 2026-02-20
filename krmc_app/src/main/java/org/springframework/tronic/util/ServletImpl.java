package org.springframework.tronic.util;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletInputStream;

public class ServletImpl extends ServletInputStream{

   private InputStream is;

   public ServletImpl(InputStream bis) {
      is = bis;
   }

   @Override
   public int read() throws IOException{
      return is.read();
   }


   @Override
   public int read(byte[] b) throws IOException {
      return is.read(b);
   }

}