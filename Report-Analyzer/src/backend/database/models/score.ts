import { SeverityLevel, CategoriesStrings } from '../../../lib/const';


export interface IScore {
  Total: number,
  Reached: number,
  Percentage: number,
  SeverityLevel: SeverityLevel
}

export type IScoreMap = {
  [identifier in CategoriesStrings]: IScore;
};


export const ScoreSchemaObject = {
  Total: Number,
  Reached: Number,
  Percentage: Number,
  SeverityLevel: String
}


export const ScoreMapSchmaObject = {
  INTEROPERABILITY: ScoreSchemaObject,
  SECURITY: ScoreSchemaObject,
  COMPLIANCE: ScoreSchemaObject
}
