package com.unkur.affnetui.file;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.fileupload.MultipartStream;

import com.unkur.affnetui.ValidationException;
import com.unkur.affnetui.config.Config;

/**
 * This class allows validation of CSV files that are uploaded to AffNet service.
 * 
 * @author Anton Lukashchuk
 *
 */
@FileExtension(".csv")
public class CsvValidator extends AbstractFileValidator{
	
	public static final char[] POSSIBLE_SEPARATORS = {'\t', ',', ';'};
	
	/**
	 * Implementation for CSV files
	 * @see com.unkur.affnetui.file.AbstractFileValidator#validateDownload(org.apache.commons.fileupload.MultipartStream)
	 */
	@Override
	public byte[] validateDownload(MultipartStream stream) throws ValidationException {
		byte[] result;
		try {
			byte[] header = getFirstLine(stream, MultipartStream.LF);
			this.validateHeader(header);
			result = header;
		} catch (Exception e) {
			throw new ValidationException("Invalid header " + e);
		}
		return result;
	}
	
	/**
	 * Get byte array from MultipartStream until first occurrence of lineEnd byte
	 * @return byte[] ending with lineEnd
	 * @throws IOException if there's no more data in given multipart straem
	 */
	private byte[] getFirstLine(MultipartStream mps, byte lineEnd) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte b = 0;
		while( b != lineEnd ) {
			b=mps.readByte();
			out.write(b);
		}
		return out.toByteArray();
	}

	/**
	 * Check first line of file for presence of correct CSV header
	 * @param firstLine
	 * @throws IOException
	 * @throws FileValidationException
	 */
	private void validateHeader(byte[] firstLine) throws ValidationException {
		boolean valid = false;
		for(char separator : POSSIBLE_SEPARATORS) {
			try {
				List<String> csvHeader = CSVParser.parseLine(firstLine, Config.ENCODING, separator);
				super.validateHeader(csvHeader);
				valid = true;
				break;
			} catch (ValidationException | IOException ignor) {}
		}
		if(!valid) {
			throw new ValidationException("Invalid header.");
		}
	}

}
