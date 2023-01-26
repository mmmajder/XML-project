export class MetadataSearchParamsDTO {
  property: string;
  value: string;
  operator: string;
  searchForNeobradjeni: boolean;
}

// export class MultipleMetadataSearchParams {
//   params: MetadataSearchParams[];
//   searchForNeobradjeni: boolean;
// }

export class TextSearchDTO {
  textSearch: string;
  casesensitive: boolean;
  searchForNeobradjeni: boolean;
}
