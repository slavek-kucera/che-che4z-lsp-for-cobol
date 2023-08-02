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
package org.eclipse.lsp.cobol.usecases;

import org.eclipse.lsp.cobol.common.AnalysisResult;
import org.eclipse.lsp.cobol.positive.CobolTextRegistry;
import org.eclipse.lsp.cobol.test.CobolText;
import org.eclipse.lsp.cobol.test.engine.UseCase;
import org.eclipse.lsp.cobol.test.engine.UseCaseUtils;
import org.eclipse.lsp.cobol.test.engine.UseCase.UseCaseBuilder;

import com.code_intelligence.jazzer.api.FuzzedDataProvider;

import java.util.ArrayList;
import static org.eclipse.lsp.cobol.common.copybook.CopybookProcessingMode.ENABLED;

/**
 * This class provides capability to run the server for actual cobol files that
 * are provided using
 * {@link CobolTextRegistry}. The positive test should always pass. If not, then
 * there are some
 * regressions. The complete error description with the file name logged.
 */
class FuzzerTest {
  public static void fuzzerTestOneInput(FuzzedDataProvider data) {
    UseCaseBuilder builder = UseCase.builder();

    StringBuilder sb = new StringBuilder();
    for (byte b : data.consumeRemainingAsBytes()) {
      if ((b < 0x20 || b >= 0x7f) && b != 0xa && b != 0xd)
        return;
      sb.append((char) b);
    }

    builder
        .documentUri("")
        .text(sb.toString())
        .copybooks(new ArrayList<CobolText>())
        .copybookProcessingMode(ENABLED);

    // if ((b & 0x80) != 0){
    // builder.sqlBackend(SQLBackend.DB2_SERVER);
    // }

    UseCase useCase = builder.build();
    AnalysisResult analyze = UseCaseUtils.analyze(useCase);
  }
}
