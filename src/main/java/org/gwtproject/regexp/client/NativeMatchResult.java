package org.gwtproject.regexp.client;

import elemental2.core.JsArray;
import elemental2.core.RegExpResult;
import org.gwtproject.regexp.shared.MatchResult;

public class NativeMatchResult implements MatchResult {

    private RegExpResult result;

    NativeMatchResult(RegExpResult result) {
        this.result = result;
    }

    @Override
    public String getGroup(int index) {
        return result.getAt(index);
    }

    @Override
    public int getGroupCount() {
        return (int) result.length;
    }

    @Override
    public int getIndex() {
        return (int) result.index;
    }

    @Override
    public String getInput() {
        return result.input;
    }
}