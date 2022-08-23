package com.my.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @author mengyuan
 * @date 2022/8/23/4:46 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 */
 class HenCoderPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        def extension = project.extensions.create('testPlugin', HenCoderExtension)
        //整个文件执行完成后，再执行
        project.afterEvaluate {
            println "Hello ${extension.name}"
        }

    }
}