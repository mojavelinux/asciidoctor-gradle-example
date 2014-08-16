package org.asciidoctor.example

import org.asciidoctor.Asciidoctor
import org.asciidoctor.extension.spi.ExtensionRegistry

class ExampleExtensionRegistry implements ExtensionRegistry {
	void register(Asciidoctor asciidoctor) {
		//asciidoctor.javaExtensionRegistry().treeprocessor ConsoleTreeprocessor
		asciidoctor.javaExtensionRegistry().block 'yell', YellBlock
	}
}
