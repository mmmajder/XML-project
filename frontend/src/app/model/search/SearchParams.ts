export class MetadataSearchParamsDTO {
  property: string;
  value: string = "";
  operator: string = "I";
  searchForNeobradjeni: boolean;
}

export class TextSearchDTO {
  textSearch: string;
  casesensitive: boolean;
  status: string;
}
