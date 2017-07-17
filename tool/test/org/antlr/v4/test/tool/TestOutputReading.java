/*
 * Copyright (c) 2012 The ANTLR Project. All rights reserved.
 * Use of this file is governed by the BSD-3-Clause license that
 * can be found in the LICENSE.txt file in the project root.
 */
package org.antlr.v4.test.tool;

import java.nio.charset.Charset;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class TestOutputReading {
	public static void append(BufferedReader in, StringBuilder buf) throws IOException {
		String line = in.readLine();
		while (line!=null) {
			buf.append(line);
			// NOTE: This appends a newline at EOF
			// regardless of whether or not the
			// input actually ended with a
			// newline.
			//
			// We should revisit this and read a
			// block at a time rather than a line
			// at a time, and change all tests
			// which rely on this behavior to
			// remove the trailing newline at EOF.
			//
			// When we fix this, we can remove the
			// TestOutputReading class entirely.
			buf.append('\n');
			line = in.readLine();
		}
	}

	/**
	 * Read in the UTF-8 bytes at {@code path}, convert all
	 * platform-specific line terminators to NL, and append NL
	 * if the file was non-empty and didn't already end with one.
	 *
	 * {@see StreamVacuum#run()} for why this method exists.
	 *
	 * Returns {@code null} if the file does not exist or the output
	 * was empty.
	 */
	public static String read(File file) throws IOException {
		// Mimic StreamVacuum.run()'s behavior of replacing all platform-specific
		// EOL sequences with NL.
		StringBuilder buf = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8")));
		try {
			append(in, buf);
		} catch (FileNotFoundException e) {
			return null;
		} finally {
			in.close();
		}
		if (buf.length() > 0) {
			return buf.toString();
		} else {
			return null;
		}
	}
}
