import request from "@/request/request";

export interface HouseModel {
  district: string;
  building: string;
  unit: string;
  number: string;
  landlordName: string;
  landlordPhone: string;
  address: string;
  price: string;
}

export function addHouse(data: HouseModel) {
  return request({
    url: "house/add",
    method: "post",
    data
  });
}
