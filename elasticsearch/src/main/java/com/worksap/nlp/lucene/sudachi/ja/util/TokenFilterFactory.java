/*
 *  Copyright (c) 2017 Works Applications Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.worksap.nlp.lucene.sudachi.ja.util;

import java.util.Map;
import java.util.Set;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.util.AbstractAnalysisFactory;
import org.apache.lucene.analysis.util.AnalysisSPILoader;

public abstract class TokenFilterFactory extends AbstractAnalysisFactory {

    private static final AnalysisSPILoader<TokenFilterFactory> loader = new AnalysisSPILoader<>(
            TokenFilterFactory.class, new String[] { "TokenFilterFactory",
                    "FilterFactory" });

    public static TokenFilterFactory forName(String name,
            Map<String, String> args) {
        return loader.newInstance(name, args);
    }

    public static Class<? extends TokenFilterFactory> lookupClass(String name) {
        return loader.lookupClass(name);
    }

    public static Set<String> availableTokenFilters() {
        return loader.availableServices();
    }

    public static void reloadTokenFilters(ClassLoader classloader) {
        loader.reload(classloader);
    }

    protected TokenFilterFactory(Map<String, String> args) {
        super(args);
    }

    public abstract TokenStream create(TokenStream input);
}
