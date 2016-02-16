package org.asciidoctor.gradle.model

import org.gradle.api.Named
import org.gradle.model.Managed

/**
 * Created by pledbrook on 25/11/2015.
 */
@Managed
interface AsciidocBackend extends Named {
    String getOutputDir()
    void setOutputDir(String dir)
}
