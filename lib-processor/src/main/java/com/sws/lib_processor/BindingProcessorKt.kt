package com.sws.lib_processor;

import com.squareup.javapoet.ClassName
import com.squareup.javapoet.MethodSpec
import com.sws.annotations.MyBindView

import java.io.IOException
import java.util.*
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Filer
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.ElementKind
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement

/**
 * @author mengyuan
 * @date 2022/8/25/4:56 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 */
class BindingProcessorKt : AbstractProcessor() {
    private var filer: Filer? = null

    /**
     * Initializes the processor with the processing environment by
     * setting the `processingEnv` field to the value of the
     * `processingEnv` argument.  An `IllegalStateException` will be thrown if this method is called
     * more than once on the same object.
     *
     * @param processingEnv environment to access facilities the tool framework
     * provides to the processor
     * @throws IllegalStateException if this method is called more than once.
     */
    override fun init(processingEnv: ProcessingEnvironment?) {
        super.init(processingEnv)
        println("BindingProcessorKt：init()")
        filer = processingEnv?.filer
    }

    /**
     * {@inheritDoc}
     */
    override fun process(
        annotations: MutableSet<out TypeElement>?,
        roundEnvironment: RoundEnvironment?
    ): Boolean {
        println("BindingProcessorKt：process()")
        for (element in roundEnvironment?.rootElements!!) {
            val packageStr: String = element.enclosingElement.toString()
            val classStr: String = element.simpleName.toString()
            val className: ClassName =
                ClassName.get(packageStr, classStr + "Binding")
            val constructorBuilder: MethodSpec.Builder =
                MethodSpec.constructorBuilder()
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(
                        ClassName.get(packageStr, classStr),
                        "activity"
                    )
            var hasBinding = false
            for (enclosedElement in element.getEnclosedElements()) {
                if (enclosedElement.getKind() == ElementKind.FIELD) {
                    val bindView: MyBindView? =
                        enclosedElement.getAnnotation(MyBindView::class.java)
                    if (bindView != null) {
                        hasBinding = true
                        constructorBuilder.addStatement(
                            "activity.\$N = activity.findViewById(\$L)",
                            enclosedElement.simpleName, bindView.value
                        )
                    }
                }
            }
            val builtClass: com.squareup.javapoet.TypeSpec =
                com.squareup.javapoet.TypeSpec.classBuilder(className)
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(constructorBuilder.build())
                    .build()
            if (hasBinding) {
                try {
                    com.squareup.javapoet.JavaFile.builder(packageStr, builtClass)
                        .build().writeTo(filer)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }

        return true
    }

    /**
     * 该解析器支持哪些注解
     */
    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        println("BindingProcessorKt：getSupportedAnnotationTypes()")
        val types = LinkedHashSet<String>()
        types.add(MyBindView::class.java.canonicalName)
        return types
    }
}