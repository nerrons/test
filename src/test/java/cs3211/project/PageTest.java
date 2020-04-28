package cs3211.project;


import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class PageTest {

    Page page = new Page("https://github.com",
            "https://github.com/pulls");

    @Test
    public void testParentUrl() {

        String parentUrl = page.getParentUrl();

        assertEquals("https://github.com", parentUrl);

    }

    @Test
    public void testUrl() {

        String Url = page.getUrl();

        assertEquals("https://github.com/pulls", Url);

    }

    @Test
    public void testContent() {

        String context = page.getContent();

        assertEquals("", context);

    }

    @Test
    public void testStatus() {

        Page.Status crawled = Page.Status.Crawled;
        assertEquals(crawled, page.getStatus());

    }

    @Test
    public void testSetParentUrl() {

        String parentUrl = page.getParentUrl();

        assertEquals("https://github.com", parentUrl);


    }


    @Test
    public void testSetUrl() {

        String Url = page.getUrl();

        assertEquals("https://github.com/pulls", Url);

    }

    @Test
    public void testSetContent() {

        page.setContent("<!DOCTYPE html>\n" +
                "<html lang=\"en\" class=\"no-js not-logged-in client-root\">\n" +
                "    <head>\n" +
                "        <meta charset=\"utf-8\">\n" +
                "        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "\n" +
                "        <title>\n" +
                "Instagram\n" +
                "</title>\n" +
                "\n" +
                "        \n" +
                "        <meta name=\"robots\" content=\"noimageindex, noarchive\">\n" +
                "        <meta name=\"apple-mobile-web-app-status-bar-style\" content=\"default\">\n" +
                "        <meta name=\"mobile-web-app-capable\" content=\"yes\">\n" +
                "        <meta name=\"theme-color\" content=\"#ffffff\">\n" +
                "\n" +
                "        <script type=\"text/javascript\">\n" +
                "        (function() {\n" +
                "  var docElement = document.documentElement;\n" +
                "  var classRE = new RegExp('(^|\\\\s)no-js(\\\\s|$)');\n" +
                "  var className = docElement.className;\n" +
                "  docElement.className = className.replace(classRE, '$1js$2');\n" +
                "})();\n" +
                "</script>\n" +
                "        <script type=\"text/javascript\">\n" +
                "(function() {\n" +
                "  if ('PerformanceObserver' in window && 'PerformancePaintTiming' in window) {\n" +
                "    window.__bufferedPerformance = [];\n" +
                "    var ob = new PerformanceObserver(function(e) {\n" +
                "      window.__bufferedPerformance.push.apply(window.__bufferedPerformance,e.getEntries());\n" +
                "    });\n" +
                "    ob.observe({entryTypes:['paint']});\n" +
                "  }\n" +
                "\n" +
                "  window.__bufferedErrors = [];\n" +
                "  window.onerror = function(message, url, line, column, error) {\n" +
                "    window.__bufferedErrors.push({\n" +
                "      message: message,\n" +
                "      url: url,\n" +
                "      line: line,\n" +
                "      column: column,\n" +
                "      error: error\n" +
                "    });\n" +
                "    return false;\n" +
                "  };\n" +
                "  window.__initialData = {\n" +
                "    pending: true,\n" +
                "    waiting: []\n" +
                "  };\n" +
                "  function asyncFetchSharedData(extra) {\n" +
                "    var sharedDataReq = new XMLHttpRequest();\n" +
                "    sharedDataReq.onreadystatechange = function() {\n" +
                "          if (sharedDataReq.readyState === 4) {\n" +
                "            if(sharedDataReq.status === 200){\n" +
                "              var sharedData = JSON.parse(sharedDataReq.responseText);\n" +
                "              window.__initialDataLoaded(sharedData, extra);\n" +
                "            }\n" +
                "          }\n" +
                "        }\n" +
                "    sharedDataReq.open('GET', '/data/shared_data/', true);\n" +
                "    sharedDataReq.send(null);\n" +
                "  }\n" +
                "  function notifyLoaded(item, data) {\n" +
                "    item.pending = false;\n" +
                "    item.data = data;\n" +
                "    for (var i = 0;i < item.waiting.length; ++i) {\n" +
                "      item.waiting[i].resolve(item.data);\n" +
                "    }\n" +
                "    item.waiting = [];\n" +
                "  }\n" +
                "  function notifyError(item, msg) {\n" +
                "    item.pending = false;\n" +
                "    item.error = new Error(msg);\n" +
                "    for (var i = 0;i < item.waiting.length; ++i) {\n" +
                "      item.waiting[i].reject(item.error);\n" +
                "    }\n" +
                "    item.waiting = [];\n" +
                "  }\n" +
                "  window.__initialDataLoaded = function(initialData, extraData) {\n" +
                "    if (extraData) {\n" +
                "      for (var key in extraData) {\n" +
                "        initialData[key] = extraData[key];\n" +
                "      }\n" +
                "    }\n" +
                "    notifyLoaded(window.__initialData, initialData);\n" +
                "  };\n" +
                "  window.__initialDataError = function(msg) {\n" +
                "    notifyError(window.__initialData, msg);\n" +
                "  };\n" +
                "  window.__additionalData = {};\n" +
                "  window.__pendingAdditionalData = function(paths) {\n" +
                "    for (var i = 0;i < paths.length; ++i) {\n" +
                "      window.__additionalData[paths[i]] = {\n" +
                "        pending: true,\n" +
                "        waiting: []\n" +
                "      };\n" +
                "    }\n" +
                "  };\n" +
                "  window.__additionalDataLoaded = function(path, data) {\n" +
                "    if (path in window.__additionalData) {\n" +
                "      notifyLoaded(window.__additionalData[path], data);\n" +
                "    } else {\n" +
                "      console.error('Unexpected additional data loaded \"' + path + '\"');\n" +
                "    }\n" +
                "  };\n" +
                "  window.__additionalDataError = function(path, msg) {\n" +
                "    if (path in window.__additionalData) {\n" +
                "      notifyError(window.__additionalData[path], msg);\n" +
                "    } else {\n" +
                "      console.error('Unexpected additional data encountered an error \"' + path + '\": ' + msg);\n" +
                "    }\n" +
                "  };\n" +
                "  \n" +
                "})();\n" +
                "</script><script type=\"text/javascript\">\n" +
                "\n" +
                "/*\n" +
                " Copyright 2018 Google Inc. All Rights Reserved.\n" +
                " Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                " you may not use this file except in compliance with the License.\n" +
                " You may obtain a copy of the License at\n" +
                "\n" +
                "     http://www.apache.org/licenses/LICENSE-2.0\n" +
                "\n" +
                " Unless required by applicable law or agreed to in writing, software\n" +
                " distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                " WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                " See the License for the specific language governing permissions and\n" +
                " limitations under the License.\n" +
                "*/\n" +
                "\n" +
                "            \n" +
                "            \n" +
                "            \n" +
                "    \n" +
                "<meta property=\"al:ios:app_name\" content=\"Instagram\" />\n" +
                "<meta property=\"al:ios:app_store_id\" content=\"389801252\" />\n" +
                "<meta property=\"al:ios:url\" content=\"instagram://mainfeed\" />\n" +
                "<meta property=\"al:android:app_name\" content=\"Instagram\" />\n" +
                "<meta property=\"al:android:package\" content=\"com.instagram.android\" />\n" +
                "<meta property=\"al:android:url\" content=\"https://www.instagram.com/_n/mainfeed/\" />\n" +
                "\n" +
                "<meta property=\"og:site_name\" content=\"Instagram\" />\n" +
                "<meta property=\"og:title\" content=\"Instagram\" />\n" +
                "<meta property=\"og:image\" content=\"/static/images/ico/favicon-200.png/ab6eff595bb1.png\" />\n" +
                "<meta property=\"fb:app_id\" content=\"124024574287414\" />\n" +
                "<meta property=\"og:url\" content=\"https://instagram.com/\" />\n" +
                "<link rel=\"canonical\" href=\"https://www.instagram.com/\" />\n" +
                "\n" +
                "\n" +
                "    <link rel=\"alternate\" href=\"https://www.instagram.com/\" hreflang=\"x-default\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=en\" hreflang=\"en\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=fr\" hreflang=\"fr\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=it\" hreflang=\"it\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=de\" hreflang=\"de\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=es\" hreflang=\"es\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=zh-cn\" hreflang=\"zh-cn\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=zh-tw\" hreflang=\"zh-tw\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=ja\" hreflang=\"ja\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=ko\" hreflang=\"ko\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=pt\" hreflang=\"pt\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=pt-br\" hreflang=\"pt-br\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=af\" hreflang=\"af\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=cs\" hreflang=\"cs\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=da\" hreflang=\"da\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=el\" hreflang=\"el\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=fi\" hreflang=\"fi\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=hr\" hreflang=\"hr\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=hu\" hreflang=\"hu\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=id\" hreflang=\"id\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=ms\" hreflang=\"ms\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=nb\" hreflang=\"nb\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=nl\" hreflang=\"nl\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=pl\" hreflang=\"pl\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=ru\" hreflang=\"ru\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=sk\" hreflang=\"sk\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=sv\" hreflang=\"sv\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=th\" hreflang=\"th\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=tl\" hreflang=\"tl\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=tr\" hreflang=\"tr\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=hi\" hreflang=\"hi\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=bn\" hreflang=\"bn\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=gu\" hreflang=\"gu\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=kn\" hreflang=\"kn\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=ml\" hreflang=\"ml\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=mr\" hreflang=\"mr\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=pa\" hreflang=\"pa\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=ta\" hreflang=\"ta\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=te\" hreflang=\"te\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=ne\" hreflang=\"ne\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=si\" hreflang=\"si\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=ur\" hreflang=\"ur\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=vi\" hreflang=\"vi\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=bg\" hreflang=\"bg\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=fr-ca\" hreflang=\"fr-ca\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=ro\" hreflang=\"ro\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=sr\" hreflang=\"sr\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=uk\" hreflang=\"uk\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=zh-hk\" hreflang=\"zh-hk\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=es-la\" hreflang=\"es-bo\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=es-la\" hreflang=\"es-ve\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=es-la\" hreflang=\"es-pe\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=es-la\" hreflang=\"es-pr\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=es-la\" hreflang=\"es-do\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=es-la\" hreflang=\"es-cr\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=es-la\" hreflang=\"es-py\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=es-la\" hreflang=\"es-co\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=es-la\" hreflang=\"es-cl\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=es-la\" hreflang=\"es-gt\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=es-la\" hreflang=\"es-pa\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=es-la\" hreflang=\"es-hn\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=es-la\" hreflang=\"es-ni\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=es-la\" hreflang=\"es-cu\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=es-la\" hreflang=\"es-ar\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=es-la\" hreflang=\"es-mx\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=es-la\" hreflang=\"es-sv\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=es-la\" hreflang=\"es-ec\" />\n" +
                "<link rel=\"alternate\" href=\"https://www.instagram.com/?hl=es-la\" hreflang=\"es-uy\" />\n" +
                "</head>\n" +
                "    <body class=\"\" style=\"\n" +
                "    background: white;\n" +
                "\">\n");

        assertNotNull(page.getContent());

    }

    @Test
    public void testSetStatus() {
        page.setStatus(Page.Status.Ignored);
        assertEquals(Page.Status.Ignored, page.getStatus());
    }

}
