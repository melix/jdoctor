/*
 * Copyright 2003-2012 the original author or authors.
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
package me.champeau.jdoctor.render;

import me.champeau.jdoctor.Problem;
import me.champeau.jdoctor.Solution;

import java.util.List;
import java.util.function.Consumer;

public class SimpleTextRenderer {
    public static <ID extends Enum<ID>, SEVERITY extends Enum<SEVERITY>, CONTEXT, PAYLOAD> String render(Problem<ID, SEVERITY, CONTEXT, PAYLOAD> problem) {
        return render("A problem happened", problem);
    }

    public static <ID extends Enum<ID>, SEVERITY extends Enum<SEVERITY>, CONTEXT, PAYLOAD> String render(String header, Problem<ID, SEVERITY, CONTEXT, PAYLOAD> problem) {
        StringBuilder sb = new StringBuilder();
        TreeNode treeNode = new TreeNode(sb, 0);
        treeNode.node(header + ": ").append(problem.getWhat());
        CONTEXT where = problem.getWhere();
        treeNode.newLine();
        treeNode.node("Where? : " + where.toString());
        problem.getWhy().ifPresent(reason -> {
            treeNode.newLine();
            treeNode.node("Why? : " + reason);
        });
        problem.getLongDescription().ifPresent(desc -> {
            treeNode.newLine();
            treeNode.node("Details: " + desc);
        });
        List<Solution> possibleSolutions = problem.getPossibleSolutions();
        if (possibleSolutions.size() == 1) {
            treeNode.newLine();
            treeNode.node("Possible solution: " + possibleSolutions.get(0).getShortDescription());
        } else if (possibleSolutions.size() > 0) {
            treeNode.newLine();
            treeNode.children("Possible solutions:", n ->
                    possibleSolutions.stream()
                            .map(Solution::getShortDescription)
                            .map(d -> "- " + d)
                            .forEachOrdered(n::node));
        }
        problem.getDocumentationLink().ifPresent(url -> {
            treeNode.newLine();
            treeNode.node("You can learn more about this problem at " + url);
        });
        return sb.toString();
    }

    private static class TreeNode {
        private final static String INDENT = "    ";
        private final StringBuilder builder;
        private final int level;
        boolean addNewLine = false;

        private TreeNode(StringBuilder builder, int level) {
            this.builder = builder;
            this.level = level;
        }

        public TreeNode children(String text, Consumer<? super TreeNode> consumer) {
            node(text);
            TreeNode childNode = new TreeNode(builder, level + 1);
            childNode.addNewLine = true;
            consumer.accept(childNode);
            return this;
        }

        public TreeNode newLine() {
            builder.append("\n");
            return this;
        }

        public TreeNode append(String text) {
            builder.append(text);
            return this;
        }

        public TreeNode node(String text) {
            if (addNewLine) {
                builder.append("\n");
            }
            for (int i = 0; i < level; i++) {
                builder.append(INDENT);
            }
            builder.append(text);
            addNewLine = true;
            return this;
        }
    }
}
