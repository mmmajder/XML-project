export class MetadataSearchParams {
  property: string;
  value: string;
  operator: string;
}

export class MultipleMetadataSearchParams {
  params: MetadataSearchParams[];
  searchForNeobradjeni: boolean;
}
