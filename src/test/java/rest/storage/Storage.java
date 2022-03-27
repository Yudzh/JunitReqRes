package rest.storage;

import rest.pojos.Unknown;
import rest.pojos.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to store expected test data.
 * Example: assertEquals(UserStorage.users.get(id).email, userFromResponse.email)
 */
public class Storage {
    public static Map<Integer, User> users = new HashMap<>();
    public static Map<Integer, Unknown> unknowns = new HashMap<>();

    static {
        users.put(1, User.builder().id(1).email("george.bluth@reqres.in").firstName("George").lastName("Bluth").build());
        users.put(2, User.builder().id(2).email("janet.weaver@reqres.in").firstName("Janet").lastName("Weaver").build());
        users.put(3, User.builder().id(3).email("emma.wong@reqres.in").firstName("Emma").lastName("Wong").build());
        users.put(4, User.builder().id(4).email("eve.holt@reqres.in").firstName("Eve").lastName("Holt").build());
        users.put(5, User.builder().id(5).email("charles.morris@reqres.in").firstName("Charles").lastName("Morris").build());
    }

    static {
        unknowns.put(1, Unknown.builder().id(1).name("cerulean").year(2000).color("#98B2D1").pantoneValue("15-4020").build());
        unknowns.put(2, Unknown.builder().id(2).name("fuchsia rose").year(2001).color("#C74375").pantoneValue("17-2031").build());
        unknowns.put(3, Unknown.builder().id(3).name("true red").year(2002).color("#BF1932").pantoneValue("19-1664").build());
        unknowns.put(4, Unknown.builder().id(4).name("aqua sky").year(2003).color("#7BC4C4").pantoneValue("14-4811").build());
        unknowns.put(5, Unknown.builder().id(5).name("tigerlily").year(2004).color("#E2583E").pantoneValue("17-1456").build());
    }
}
