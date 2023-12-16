package com.bank.antifraud.mappers;

import com.bank.antifraud.dto.AuditDto;
import com.bank.antifraud.entity.AuditEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class AuditMapperTest {

    @InjectMocks
    private AuditMapperImpl mapper;

    @Test
    @DisplayName("маппинг в Dto")
    public void toEntityTest() {
        final AuditEntity audit = new AuditEntity();
        audit.setId(1L);
        audit.setEntityType("EntityType");
        audit.setOperationType("OperationType");
        audit.setCreatedBy("CreatedBy");
        audit.setModifiedBy("ModifiedBy");
        audit.setCreatedAt(new Timestamp(100));
        audit.setModifiedAt(new Timestamp(100));
        audit.setNewEntityJson("NewEntityJson");
        audit.setEntityJson("EntityJson");


        final AuditDto result = mapper.toDto(audit);
        assertAll(
                () -> assertEquals(audit.getId(), result.getId()),
                () -> assertEquals(audit.getEntityType(), result.getEntityType()),
                () -> assertEquals(audit.getOperationType(), result.getOperationType()),
                () -> assertEquals(audit.getCreatedBy(), result.getCreatedBy()),
                () -> assertEquals(audit.getModifiedBy(), result.getModifiedBy()),
                () -> assertEquals(audit.getCreatedAt(), result.getCreatedAt()),
                () -> assertEquals(audit.getModifiedAt(), result.getModifiedAt()),
                () -> assertEquals(audit.getNewEntityJson(), result.getNewEntityJson()),
                () -> assertEquals(audit.getEntityJson(), result.getEntityJson())
        );
    }

    @Test
    @DisplayName("маппинг в Dto, на вход подан null")
    public void toEntityNullTest() {
        final AuditDto result = mapper.toDto(null);

        assertNull(result);
    }
}
