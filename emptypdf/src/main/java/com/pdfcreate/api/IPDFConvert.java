package com.pdfcreate.api;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public interface IPDFConvert<T> {
	 /**
     * This method attempts to export the data encapsulated with the specified object into a file.
     *
     * @param object
     *         Object that needs to be exported.
     * @param fileName
     *         Output file name.
     *
     * @return File instance of type {@link File} that contains the data.
	 * @throws IOException 
     */
	File export(T object, String fileName) throws IOException;
	 /**
     * This method attempts to export the data encapsulated withn  the Collection of Sheets   into a file.
     *
     * @param object
     *         Object that needs to be exported.
     * @param fileName
     *         Output file name.
     *
     * @return File instance of type {@link File} that contains the data.
	 * @throws IOException 
     */
	 File export(Collection<ISheet> pages, String Filename) throws IOException;

}
