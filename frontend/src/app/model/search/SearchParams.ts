export class MetadataSearchParamsDTO {
  property: string;
  value: string = "";
  operator: string = "I";
  status: string;
}

export class TextSearchDTO {
  textSearch: string;
  casesensitive: boolean;
  status: string;
}
