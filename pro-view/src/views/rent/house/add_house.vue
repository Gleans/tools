<template>
  <div>
    <el-row :gutter="15">
      <el-form ref="houseForm" :model="formData" :rules="rules" size="medium" label-width="100px">
        <el-col :span="24">
          <div class="cfa_card">
            <div class="cfa_card_title">基本信息</div>
            <el-row>
              <el-col :span="7">
                <el-form-item label="小区名称" prop="district">
                  <el-input v-model="formData.district" placeholder="请输入小区名称" clearable
                            :style="{width: '100%'}"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="7">
                <el-form-item label="楼号" prop="building">
                  <el-input v-model="formData.building" placeholder="请输入楼号" />
                </el-form-item>
              </el-col>
              <el-col :span="7">
                <el-form-item label="房间号" prop="number">
                  <el-input v-model="formData.number" placeholder="房间号" />
                </el-form-item>
              </el-col>
              <el-col :span="15">
                <el-form-item label="地址" prop="address">
                  <el-input v-model="formData.address" placeholder="请输入地址" :autosize="{minRows: 4, maxRows: 4}"
                            :style="{width: '100%'}"></el-input>
                </el-form-item>
              </el-col>
            </el-row>

          </div>
        </el-col>

        <el-col :span="24">
          <div class="cfa_card">
            <div class="cfa_card_title">房东信息</div>
            <el-row>
              <el-col :span="10">
                <el-form-item label="房东姓名" prop="landlordName">
                  <el-input v-model="formData.landlordName" placeholder="请输入房东姓名" clearable
                            :style="{width: '100%'}"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="10">
                <el-form-item label="房东手机号" prop="landlordPhone">
                  <el-input v-model="formData.landlordPhone" placeholder="请输入房东手机号" clearable
                            :style="{width: '100%'}"></el-input>
                </el-form-item>
              </el-col>
            </el-row>

          </div>
        </el-col>
        <el-col :span="24">
          <div class="cfa_card">
            <div class="cfa_card_title">其他信息</div>
            <el-row>
              <el-col :span="10">
                <el-form-item label="房产面积" prop="square">
                  <el-input v-model="formData.square" placeholder="房产面积">
                    <template #append>平方米</template>
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="10">
                <el-form-item label="起租日期" prop="startDateStr">
                  <el-date-picker v-model="formData.startDate" placeholder="请选择起租日期" clearable />
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-row>

                  <el-col :span="10">
                    <el-form-item label="交租间隔" prop="intervalMonth">
                      <el-input v-model="formData.intervalMonth" placeholder="请选择/月交租间隔">
                        <template #append>个月</template>
                      </el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="10">
                    <el-form-item label="提前付租" prop="aheadDays">
                      <el-input v-model="formData.aheadDays" placeholder="提前几天">
                        <template #append>天</template>
                      </el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="10">
                    <el-form-item label="装修期" prop="decoration">
                      <el-input v-model="formData.decoration" :disabled="true" clearable>
                        <template #append>天</template>
                      </el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="10">
                    <el-form-item label="租期" prop="continueYears">
                      <el-select v-model="formData.continueYears" placeholder="请选择">
                        <el-option
                            v-for="item in options"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                </el-row>
              </el-col>
              <el-col :span="12">
                <el-form-item label="到期日期" prop="endDateStr">
                  <el-date-picker v-model="formData.endDate" placeholder="请选择到期日期" clearable/>
                </el-form-item>
              </el-col>
              <!-- <el-col :span="13">
                <el-form-item label="房屋图片" prop="field111">
                  <el-upload ref="field111" :file-list="imgList" :action="field111Action" multiple :before-upload="field111BeforeUpload" list-type="picture" accept="image/*">
                    <el-button size="small" type="primary" icon="el-icon-upload">点击上传</el-button>
                    <div slot="tip" class="el-upload__tip">只能上传不超过 10MB 的image/*文件</div>
                  </el-upload>
                </el-form-item>
              </el-col> -->
            </el-row>
          </div>
        </el-col>

        <el-col :span="24">
          <el-form-item size="large">
            <el-button type="primary" @click="submitForm">提交</el-button>
            <el-button @click="resetForm">重置</el-button>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>
  </div>
</template>
<script>
import { defineComponent, reactive, ref, onMounted, toRefs } from "vue";
import { useRouter } from "vue-router";
import { houseList } from "@/api/house";
import { ElNotification } from "element-plus";

const { inject } = require("vue");
export default defineComponent({
  name: "addHouse",
  setup() {
    const data = reactive({
      formData: {
        building: 1,
        number: undefined,
        address: undefined,
        landlordName: undefined,
        landlordPhone: "",
        square: undefined,
        intervalMonth: 3,
        decoration: null,
        continueYears: "五年",
        aheadDays: 7,
        startDate: 1618243200000,
        endDate: 1618243200000
      },
      rules: {
        district: [
          {
            required: true,
            message: "请输入小区名称",
            trigger: "blur"
          }
        ],
        building: [
          {
            required: true,
            message: "请输入楼号",
            trigger: "blur"
          }
        ],
        number: [
          {
            required: true,
            message: "房间号",
            trigger: "blur"
          }
        ],
        address: [
          {
            required: true,
            message: "请输入地址",
            trigger: "blur"
          }
        ],
        landlordName: [
          {
            required: true,
            message: "请输入房东姓名",
            trigger: "blur"
          },
          {
            pattern: /^[\u4E00-\u9FA5]+$/,
            message: "房东姓名只能为中文",
            trigger: "blur"
          }
        ],
        square: [],
        field112: [
          {
            required: true,
            message: "请选择起租日期",
            trigger: "change"
          }
        ],
        intervalMonth: [
          {
            required: true,
            message: "请选择/月交租间隔",
            trigger: "change"
          }
        ],
        field117: [
          {
            required: true,
            message: "提前几天",
            trigger: "blur"
          }
        ],
        endDate: [
          {
            required: true,
            message: "请选择到期日期",
            trigger: "change"
          }
        ]
      },
      field111Action: "/pic/upload",
      imgList: "",
      options: [{
        value: '3',
        label: '三年'
      }, {
        value: '4',
        label: '四年'
      }, {
        value: '5',
        label: '五年'
      }, {
        value: '6',
        label: '六年'
      }, {
        value: '7',
        label: '7年'
      }],
      value: ''
    });
    data.formData = {
      district:  "海上华庭",
      building:  26,
      number:  301,
      landlordPhone:  17053535666,
      address:  "九商路888弄",
      landlordName:  "刘强东",
      square:  160.25,
      startDate: new Date().getMilliseconds(),
      continueYears: "5年",
    }
    const DateUtil = inject('DateUtil');
    // 获取年份
    let continueYearsVal=data.formData.continueYears.replace("年","")
    let countYearNum = Number.parseInt(continueYearsVal)

    console.log("test")
    console.log(DateUtil.addMonth(1618277797000,8))
    // data.formData.endDate =
    // let tempDate = new Date(this.formData.startDateStr);
    // let lastYear = tempDate.getFullYear() + this.formData.continueYears;
    // let lastDay = tempDate.getDate() - 1;
    function submitForm() {
      console.log(data.formData.startDate);
      // this.$refs["houseForm"].validate(valid => {
      //   if (!valid) return;
      //   console.log(this.formData);
      //   // 时间转时间戳
      //   this.formData.startDate = new Date(
      //       this.formData.startDateStr
      //   ).getTime();
      //   this.formData.endDate = new Date(this.formData.endDateStr).getTime();
      //   // 新增接口调用
      //   addHouse(this.formData).then(res => {
      //     console.log(res);
      //   });
      // });
    }

    return {
      ...toRefs(data),
      submitForm
    };
  }

});


// export default {
//   components: {},
//   props: [],
//   data() {
//     return {
//
//     };
//   },
//   computed: {
//     continueYears() {
//       return this.formData.continueYears;
//     }
//   },
//   watch: {
//     "formData.continueYears": function(val, oldval) {
//       //string 转换为date
//       const numReg: any = /^[0-9]*$/;
//       let numRe: RegExp= new RegExp(numReg);
//       if (!numRe.test(val)) {
//         this.formData.endDateStr = new Date().setFullYear(
//           new Date(this.formData.startDateStr).getFullYear() + val
//         );
//       }
//     } //键路径必须加上引号
//   }
// created() {
//   this.initData();
// }
//   mounted() {},
//   methods: {
// ,
//     resetForm() {
//       this.$refs["houseForm"].resetFields();
//     },
//     field111BeforeUpload(file) {
//       let isRightSize = file.size / 1024 / 1024 < 10;
//       if (!isRightSize) {
//         this.$message.error("文件大小超过 10MB");
//       }
//       let isAccept = new RegExp("image/*").test(file.type);
//       if (!isAccept) {
//         this.$message.error("应该选择image/*类型的文件");
//       }
//       return isRightSize && isAccept;
//     },
//     initData() {

//       //计算装修期
//       let currentDate = new Date();
//       let startDate = new Date(this.formData.startDateStr);
//       currentDate = new Date(
//         currentDate.getFullYear(),
//         currentDate.getMonth(),
//         currentDate.getDate()
//       );
//       startDate = new Date(
//         startDate.getFullYear(),
//         startDate.getMonth(),
//         startDate.getDate()
//       );
//       let day = currentDate.getTime() - startDate.getTime();
//       this.formData.decoration = day / (24 * 60 * 60 * 1000);
//     },
//     continueYearsChange(value) {}
//   }
// };
</script>
<style>
/*.el-upload__tip {*/
/*  !*line-height: 1.2;*!*/
/*}*/

.cfa_card {
  border: 1px solid #eceef5;
  font-family: Microsoft YaHei, serif;
  font-size: 14px;
  color: #606266;
  font-weight: 700;
  padding: 20px;
  margin-bottom: 20px;
  display: block;
  margin-top: 10px;
}

.cfa_card_title {
  background-color: white;
  position: relative;
  left: 30px;
  width: calc(10vh);
  top: -30px;
  text-align: center;
}

label {
  font-weight: 400 !important;
}
</style>
