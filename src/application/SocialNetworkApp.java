package application;

import helper.ParseCommandHelper;

class SocialNetworkApp {

    public static void main(String[] args) {
        new NetworkTopologyApp();
    }

    SocialNetworkApp() {
        ParseCommandHelper.command();
    }
}
