package com.venturocket.api.client.listing.object;

import com.google.common.base.CaseFormat;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * User: Joe Linn
 * Date: 1/4/14
 * Time: 7:22 PM
 */
public class ListingType {
    public Type type;

    @JsonSerialize(using = ListingTypeSerializer.class)
    public static enum Type{
        CONTRACT,
        FULL_TIME,
        PART_TIME,
        CONTRACT_TO_HIRE,
        INTERNSHIP;

        @JsonCreator
        public static Type create(String name){
            return Type.valueOf(CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_UNDERSCORE, name));
        }

        @Override
        public String toString() {
            return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, name());
        }
    }
}
