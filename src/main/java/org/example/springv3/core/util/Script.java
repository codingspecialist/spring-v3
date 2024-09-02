package org.example.springv3.core.util;

public class Script {

    public static String back(String msg) {
        String errMsg = """
                <script>
                    alert('$msg');
                    history.back();
                </script>
                """.replace("$msg", msg);
        return errMsg;
    }

    public static String href(String msg, String url) {
        String errMsg = """
                <script>
                    alert('$msg');
                    location.href = '$url';
                </script>
                """.replace("$msg", msg)
                .replace("$url", url);
        return errMsg;
    }
}
