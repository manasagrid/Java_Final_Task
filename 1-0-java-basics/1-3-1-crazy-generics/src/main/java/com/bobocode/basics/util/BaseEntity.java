package com.bobocode.basics.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {
    protected UUID uuid;
    protected LocalDateTime createdOn;

    // Constructor that sets the creation time to the current date-time
    public BaseEntity(UUID uuid) {
        this.uuid = uuid;
        this.createdOn = LocalDateTime.now();
    }

    // Equality check based on UUID for entity uniqueness
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BaseEntity that = (BaseEntity) obj;
        return Objects.equals(uuid, that.uuid);
    }

    // Hash code based on UUID for consistent hashing in collections
    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    // Custom toString for clear debugging output
    @Override
    public String toString() {
        return String.format("BaseEntity{uuid=%s, createdOn=%s}", uuid, createdOn);
    }
}

