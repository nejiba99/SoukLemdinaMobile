/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.souklemdina.entities.Profile;
import com.souklemdina.entities.Post;
import com.souklemdina.entities.PostHome;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rkader
 */
public class PostServices {

    public ArrayList<PostHome> getAccueil(Integer id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukLemdina/web/social/postsHomeWS/" + id;
        con.setUrl(Url);
        ArrayList<PostHome> mapPosts = new ArrayList<>();
        con.addResponseListener((e) -> {
            PostServices ser = new PostServices();
            String jsonRes = new String(con.getResponseData());
            try {
                JSONParser j = new JSONParser();

                Map<String, Object> posts = j.parseJSON(new CharArrayReader(jsonRes.toCharArray()));

                List<Map<String, Object>> list = (List<Map<String, Object>>) posts.get("root");

                for (Map<String, Object> obj : list) {
                    Post pos = new Post((int) Float.parseFloat(obj.get("idPos").toString()),
                            new Date((long) Float.parseFloat(obj.get("datePos").toString())),
                            obj.get("textePos").toString(),
                            obj.get("titrePos").toString(),
                            null == (obj.get("nbSignalPos")) ? 0 : (int) Float.parseFloat(obj.get("nbSignalPos").toString()),
                            obj.get("imagePos").toString(),
                            new Date((long) Float.parseFloat(obj.get("updatedAtPos").toString())),
                            (int) Float.parseFloat(obj.get("idUserPos").toString()));
                    Profile pr = new Profile((int) Float.parseFloat(obj.get("idPr").toString()),
                            obj.get("taglinePr").toString(),
                            obj.get("imagePr").toString(),
                            new Date((long) Float.parseFloat(obj.get("updatedAtPr").toString())),
                            obj.get("aboutMePr").toString(),
                            (int) Float.parseFloat(obj.get("idUserPr").toString()));
                    mapPosts.add(new PostHome(pr, pos, obj.get("firstname").toString(), obj.get("lastname").toString(),
                            obj.get("mfollowi").toString().equals("true")));
                }

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        return mapPosts;
    }

    public void addPost(Post p, Integer id) {
        try {
            ConnectionRequest connReq = new ConnectionRequest();
            connReq.setPost(true);
            connReq.setContentType("application/json");
            connReq.setUrl("http://localhost/SoukLemdina/web/social/addPostWS/" + id + "/" + p.getTitre() + "/" + p.getTexte() + "/" + p.getImage());
            connReq.addResponseListener((e) -> {
                String str = new String(connReq.getResponseData());
                System.out.println(str);
            });
            NetworkManager.getInstance().addToQueueAndWait(connReq);
        } catch (Exception err) {
            System.err.println(err.getMessage());
        }
    }
}
