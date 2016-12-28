/*
 * Copyright (c) 2012 The ANTLR Project. All rights reserved.
 * Use of this file is governed by the BSD-3-Clause license that
 * can be found in the LICENSE.txt file in the project root.
 */
package org.antlr.v4.codegen.model;

import org.antlr.v4.codegen.OutputModelFactory;

public class ListenerDispatchMethod extends DispatchMethod {
	public boolean isEnter;

	public ListenerDispatchMethod(OutputModelFactory factory, boolean isEnter) {
		super(factory);
		this.isEnter = isEnter;
	}
}
