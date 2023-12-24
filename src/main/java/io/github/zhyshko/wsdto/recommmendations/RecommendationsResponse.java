package io.github.zhyshko.wsdto.recommmendations;

import java.util.UUID;

public record RecommendationsResponse(UUID externalId, Long score) {
}
