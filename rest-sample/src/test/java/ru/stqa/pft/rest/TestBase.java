package ru.stqa.pft.rest;


import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;

public class TestBase {



    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    public  boolean isIssueOpen(int issueId) throws IOException {
        String json = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues/" + issueId + ".json")).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        JsonElement issue = issues.getAsJsonArray().get(0);
        String state = issue.getAsJsonObject().get("state_name").toString();
        if (state.equals("\\”Closed\\”")) return false;
        if (state.equals("\"Resolved\"")) return false;
        if (state.equals("\"Deleted\"")) return false;
        else return true;

    }

    public Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }
}
