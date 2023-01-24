export class MetadataSearchParams {
  property: string;
  value: string;
  operator: string;
}

export class MultipleMetadataSearchParams {
  params: MetadataSearchParams[];
  searchForNeobradjeni: boolean;
}

export class TextSearchDTO {
  textSearch: string;
  casesensitive: boolean;
  searchForNeobradjeni: boolean;
}
