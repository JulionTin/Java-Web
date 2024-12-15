/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.uap.admin;

import java.util.HashMap;
import java.util.Map;


public class Response {
    
    private final HashMap<String, String> responses;
    
    public Response() {
        responses = new HashMap<>();
        initializeResponses();
    }

    private void initializeResponses() {
        responses.put("hello", "Hi there! How can I assist you today?");
        responses.put("how are you", "I'm just a bot, but I'm functioning perfectly!");
        responses.put("bye", "Goodbye! Have a great day!");
        responses.put("thanks", "You're welcome! Let me know if there's anything else.");
        responses.put("hello", "Hi there! How can I assist you today?");
        responses.put("hi", "Hello! What can I help you with?");
        responses.put("how do I upload artwork", "To upload artwork, go to your profile, click 'edit', and you should see the button for saving an artwork.");
        responses.put("how do I create a section", "Creating a section for your gallery is easy! Head to your profile, click 'edit', and you should see the button for adding a new section.");
        responses.put("what file formats are supported", "We currently support JPEG, PNG, and GIF file formats.");
        responses.put("how do I change my profile picture", "Go to your profile settings, click on your current picture, and upload a new one.");
        responses.put("are there size limits for uploads", "Yes, each file should not exceed 15MB in size.");
        responses.put("what is this website about", "This is a platform for artists to showcase their galleries, upload artworks, and connect with art lovers!");
        responses.put("how do I report inappropriate content", "Click the 'Report' button near the artwork");
        responses.put("can I comment on artworks", "Yes, you can! Simply scroll down to the comments section under the artwork and type your message.");
        responses.put("is there a way to search for specific artists", "Yes! Use the search bar at the top of the page to find artists");
        responses.put("what is a featured gallery", "The featured gallery showcases outstanding artists selected by our curators.");
        responses.put("how do I edit my gallery", "Go to your profile, and click the 'edit' button to make changes.");
        responses.put("can I upload videos", "as an art gallery website, we only support image formats");
        responses.put("where can I see my uploaded artworks", "Visit your profile to see all your uploaded artworks and galleries.");
        responses.put("how do I get featured", "Our curators select featured content based on quality, uniqueness, and popularity. Keep creating amazing work!");
        responses.put("is my artwork protected from theft", "We recommend you use watermarking and other measures to help protect your artwork before uploading.");
        responses.put("help", "Sure! Here are some things you can ask me:\n" +
        "- 'How do I upload artwork?'\n" +
        "- 'What file formats are supported?'\n" +
        "- 'How do I edit my gallery'\n" +
        "- 'How do I report inappropriate content?'\n" +
        "- 'What is a featured gallery?'\n" +
        "Let me know how else I can assist!");
    }
    
    public String getResponse(String input) {
        String defaultResponse = "I'm sorry, I didn't understand that. You may rephrase your question or type \"help\" to look at the list of questions you may ask me";
        if (input == null || input.trim().isEmpty()) {
            return "Please type something in the chatbox";
        }
        return responses.getOrDefault(input.toLowerCase().trim(), defaultResponse);
    }
}
