/*******************************************************************************
 * Copyright (c) 2007, 2008 IBM Corporation and others.
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
package org.eclipse.jface.text.hyperlink;


/**
 * Extends {@link IHyperlinkDetector} with ability
 * to dispose a hyperlink detector.
 * <p>
 * Clients may implement this interface.
 * </p>
 *
 * @since 3.3
 */
public interface IHyperlinkDetectorExtension {

	/**
	 * Disposes this hyperlink detector.
	 */
	void dispose();

}