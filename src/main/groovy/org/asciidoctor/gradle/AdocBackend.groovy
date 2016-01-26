package org.asciidoctor.gradle

import org.gradle.api.Named
import org.gradle.model.Managed

/**
 * Created by pledbrook on 25/11/2015.
 */
@Managed
interface AdocBackend extends Named {
    String getOutputDir()
    void setOutputDir(String dir)
}
