import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public final class File {

	private String filePath;
	private BufferedReader reader;
	private BufferedWriter writer;

	public File(String filePath) {
		this.filePath = filePath;
	}

	public void open(char mode) {
		if (null != reader || null != writer) {
			throw new IllegalStateException("File already opened");
		}

		if ('R' == mode || 'r' == mode) {
			try {
				reader = new BufferedReader(new FileReader(filePath));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		} else if ('W' == mode || 'w' == mode) {	
			try {
				writer = new BufferedWriter(new FileWriter(filePath));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public void close() {
		if (null != reader) {
			try {
				reader.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		if (null != writer) {
			try {
				writer.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public String readLine() {
		String line;

		try {
			line = reader.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		if (null == line) {
			return null;
		}

		return line;
	}
	
	public void writeLine(String s) {
		try {
			writer.write(s + "\n");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
