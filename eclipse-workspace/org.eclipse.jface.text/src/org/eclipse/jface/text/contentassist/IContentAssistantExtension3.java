/*******************************************************************************
 * Copyright (c) 2006, 2008 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jface.text.contentassist;

import org.eclipse.jface.bindings.keys.KeySequence;

/**
 * Extends {@link org.eclipse.jface.text.contentassist.IContentAssistant} with the following
 * function:
 * <ul>
 * <li>a key-sequence to listen for in repeated invocation mode</li>
 * </ul>
 *
 * @since 3.2
 */
public interface IContentAssistantExtension3 {

	/**
	 * Sets the key sequence to listen for in repeated invocation mode. If the key sequence is
	 * encountered, a step in the repetition iteration is triggered.
	 *
	 * @param sequence the key sequence used for the repeated invocation mode or <code>null</code> if none
	 */
	public void setRepeatedInvocationTrigger(KeySequence sequence);
}
