package com.ryanharis.app.match.persistence.entities;

import java.io.Serializable;

public interface IDomainObject extends Serializable {
  Serializable getId();
}