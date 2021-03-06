/*
 * Copyright (c) 2018-present, Jim Kynde Meyer
 * All rights reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.intellij.lang.jsgraphql.endpoint.ide.startup;

import com.intellij.lang.jsgraphql.schema.GraphQLSchemaChangeListener;
import com.intellij.lang.jsgraphql.v1.ide.project.JSGraphQLLanguageUIProjectService;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import org.jetbrains.annotations.NotNull;

/**
 * Starts up the UI Service during project startup
 */
public class GraphQLStartupActivity implements StartupActivity, DumbAware {

    @Override
    public void runActivity(@NotNull Project project) {

        // startup schema change listener
        GraphQLSchemaChangeListener.getService(project);

        if(ApplicationManager.getApplication().isUnitTestMode()) {
            // don't create the UI when unit testing
            return;
        }
        // startup the UI service
        JSGraphQLLanguageUIProjectService.getService(project);
    }
}
