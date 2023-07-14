export type CarModelsJson = {
  data: {
    car_Model_Lists: {
      results: Array<CarModel>
    }
  }
};

export type CarModel = {
  Category: string;
  Make: string;
  Model: string;
  Year: number;
}
