package com.codingmogul.microservices.userservice.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserNo implements Serializable {
    private Long id;
}
