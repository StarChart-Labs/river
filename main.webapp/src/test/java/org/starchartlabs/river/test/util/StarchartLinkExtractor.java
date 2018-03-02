package org.starchartlabs.river.test.util;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.restdocs.hypermedia.Link;
import org.springframework.restdocs.hypermedia.LinkExtractor;
import org.springframework.restdocs.operation.OperationResponse;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("unchecked")
public class StarchartLinkExtractor implements LinkExtractor {

    private final ObjectMapper objectMapper = new ObjectMapper();
    
    // TODO nickavv pending Spring Restdoc accepting my pull request, 
    // this class could extend AbstractJsonLinkExtractor and be simplified
    @Override
    public Map<String, List<Link>> extractLinks(OperationResponse response)
            throws IOException {
        Map<String, Object> jsonContent = this.objectMapper
                .readValue(response.getContent(), Map.class);
        return extractLinks(jsonContent);
    }

    private Map<String, List<Link>> extractLinks(Map<String, Object> json) {
        MultiValueMap<String, Link> extractedLinks = new LinkedMultiValueMap<>();
        Object metadata = json.get("_meta");
        if (metadata instanceof Map) {
            Object possibleLinks = ((Map<String, Object>) metadata).get("links");
            if (possibleLinks instanceof Collection) {
                Collection<Object> linksCollection = (Collection<Object>) possibleLinks;
                for (Object linkObject : linksCollection) {
                    if (linkObject instanceof Map) {
                        Link link = maybeCreateLink((Map<String, Object>) linkObject);
                        maybeStoreLink(link, extractedLinks);
                    }
                }
            }
        }
        return extractedLinks;
    }
    
    private static Link maybeCreateLink(Map<String, Object> linkMap) {
        Object hrefObject = linkMap.get("href");
        Object relObject = linkMap.get("rel");
        if (relObject instanceof String && hrefObject instanceof String) {
            Object titleObject = linkMap.get("title");
            return new Link((String) relObject, (String) hrefObject,
                    titleObject instanceof String ? (String) titleObject : null);
        }
        return null;
    }

    private static void maybeStoreLink(Link link,
            MultiValueMap<String, Link> extractedLinks) {
        if (link != null) {
            extractedLinks.add(link.getRel(), link);
        }
    }

}
