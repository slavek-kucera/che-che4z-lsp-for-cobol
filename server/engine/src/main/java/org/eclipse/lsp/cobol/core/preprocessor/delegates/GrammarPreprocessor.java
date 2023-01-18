/*
 * Copyright (c) 2020 Broadcom.
 * The term "Broadcom" refers to Broadcom Inc. and/or its subsidiaries.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Broadcom, Inc. - initial API and implementation
 *
 */
package org.eclipse.lsp.cobol.core.preprocessor.delegates;

import lombok.NonNull;
import org.eclipse.lsp.cobol.common.ResultWithErrors;
import org.eclipse.lsp.cobol.common.copybook.CopybookConfig;
import org.eclipse.lsp.cobol.common.mapping.DocumentMap;
import org.eclipse.lsp.cobol.core.model.OldExtendedDocument;
import org.eclipse.lsp.cobol.core.preprocessor.CopybookHierarchy;

/** This interface represents service to build the extended document */
public interface GrammarPreprocessor {

  /**
   * Build extended document using its COPY statements, excluding non-processable statements,
   * applying related semantic analysis
   *
   * @param documentMap - current document map
   * @param copybookConfig - contains config info like: copybook processing mode, target backend sql
   *     server
   * @param hierarchy the hierarchy of the copybooks
   * @return extended document with copybooks and related errors
   */
  @NonNull
  ResultWithErrors<OldExtendedDocument> buildExtendedDocument(
      @NonNull DocumentMap documentMap,
      @NonNull CopybookConfig copybookConfig,
      @NonNull CopybookHierarchy hierarchy);
}
