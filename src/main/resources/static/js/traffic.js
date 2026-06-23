// initialize variables (Khởi tạo biến)
let pointMake = null;
const API_KEY_TOMTOM = "dRrxO1yXAGX2V9Ee1RSxqG6qT0Gb64j3";
const API_KEY_AQI = "40e5dcd4ef8dc3c01daf8e51936235f7c3f471f9";
const arrCpModel = [];
let currentTrafficId = null;
let currentPage = 0;
const pageSize = 5;
// querySelector(bộ chọn truy vấy): truy vấn với tới class or id của tags
const inputShowLat = document.querySelector("#inputShowLat");
const inputShowLng = document.querySelector("#inputShowLng");
const inputShowAddress = document.querySelector("#inputShowAddress");
const chartTrafficSpeed = document.querySelector("#ChartTrafficSpeed");
const cpModalTraffic = document.querySelector("#modal-traffic");
const cpModalDeleteTraffic = document.querySelector("#modal-delete-traffic");
const cpModalDetailTS = document.querySelector("#modal-detail-TS");
const BmodalTraffic = new bootstrap.Modal(cpModalTraffic);
const BmodalDeleteTraffic = new bootstrap.Modal(cpModalDeleteTraffic);
const BmodalDetailTS = new bootstrap.Modal(cpModalDetailTS);
const getModalTraffic = bootstrap.Modal.getInstance(cpModalTraffic);
const formTraffic = document.querySelector("#trafficForm");
const titleModelTraffic = document.querySelector("#modalTitle");
const btnSubmitTraffic = document.querySelector("#SubmitTrafficInfo");
const cpInputTrafficId = document.querySelector("#cpInputTrafficId");
const inputShowId = document.querySelector("#inputShowID");
const formDeleteTraffic = document.querySelector("#trafficFormDelete");
const spanTrafficInfoId = document.querySelector("#trafficinfoId");
// lấy bản đồ
const map = L.map("map").setView([21.0285, 105.8542], 12);
L.tileLayer("https://tile.openstreetmap.org/{z}/{x}/{y}.png", {
  maxZoom: 19,
  attribution:
    '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
}).addTo(map);

// hàm click lấy điểm tọa độ point: latitude và longitude
const onMapClick = async (e) => {
  const lat = e.latlng.lat;
  const lng = e.latlng.lng;
  if (pointMake) {
    // kill ponit
    map.removeLayer(pointMake);
  }
  pointMake = L.marker([lat, lng])
    .addTo(map)
    .bindPopup(`Lat: ${lat}<br>Lng: ${lng}`)
    .openPopup();
  try {
    const response = await axios.get(
      `https://nominatim.openstreetmap.org/reverse?format=json&lat=${lat}&lon=${lng}`,
    );

    const result = response.data.display_name;
    if (getModalTraffic?._isShown) {
      inputShowLat.value = lat;
      inputShowLng.value = lng;
      inputShowAddress.value = result;
    }
  } catch (err) {
    console.error("Error: get address:", err);
    return "Error";
  }
};
map.on("click", onMapClick);

// hàm mở modal traffic
function openCreateTraffic() {
  BmodalTraffic.show();
  titleModelTraffic.textContent = "Create Traffic";
  btnSubmitTraffic.textContent = "Create traffic";
  // formTraffic.setAttribute("th:action", "@{/add-traffic}");
  formTraffic.action = "/add-traffic";
}
function openUpdateTraffic(value) {
  inputShowId.value = value.dataset.id;
  inputShowLat.value = value.dataset.lat;
  inputShowLng.value = value.dataset.lng;
  inputShowAddress.value = value.dataset.address;
  BmodalTraffic.show();
  titleModelTraffic.textContent = "Update Traffic";
  btnSubmitTraffic.textContent = "Update traffic";
  cpInputTrafficId.style = "display:flex";
  formTraffic.action = `/update/${value.dataset.id}`;
  pointMake = L.marker([value.dataset.lat, value.dataset.lng])
    .addTo(map)
    .bindPopup(`Lat: ${value.dataset.lat}<br>Lng: ${value.dataset.lng}`)
    .openPopup();
}

function openDeleteTraffic(value) {
  BmodalDeleteTraffic.show();
  formDeleteTraffic.action = `/delete/${value.dataset.id}`;
}

async function openDetailTrafficSpeed(value, page = 0) {
  currentTrafficId = value.dataset.id;

  currentPage = page;

  spanTrafficInfoId.textContent = `Traffic-Info-ID: ${currentTrafficId}`;

  BmodalDetailTS.show();

  try {
    const res = await fetch(
      `/get-traffic-info/${currentTrafficId}?page=${page}&size=${pageSize}`,
    );

    const data = await res.json();

    renderTrafficTable(data);

    renderPagination(data);
  } catch (err) {
    console.error(err);
  }
}

function renderTrafficTable(data) {
  const tbody = document.querySelector("#modal-detail-TS tbody");

  tbody.innerHTML = "";

  let html = "";

  data.content.forEach((v, index) => {
    const date = new Date(v.createdAt);

    const formatted = date.toLocaleString("vi-VN", {
      day: "2-digit",
      month: "2-digit",
      year: "numeric",
      hour: "2-digit",
      minute: "2-digit",
    });
    html += `

<tr>

<td>
${data.number * data.size + index + 1}
</td>

<td>
${v.trafficSpeedId}
</td>

<td>
${v.frc}
</td>

<td>
${v.currentSpeed}
</td>

<td>
${v.freeFlowSpeed}
</td>

${v.trafficLevel == "LOW" ? '<td style="color:blue">LOW</td>' : v.trafficLevel == "MEDIUM" ? '<td style="color:yellow">MEDIUM</td>' : '<td style="color:red">HIGH</td>'}

<td>
${v.currentTravelTime}
</td>

<td>
${v.freeFlowTravelTime}
</td>

<td>
${v.confidence}
</td>

${v.roadClosure == false ? "<td>Đang hoạt động</td>" : "<td>Đang bảo trì</td>"}

<td style="text-align:center">
${formatted}
</td>
</tr>

`;
  });

  tbody.innerHTML = html;
}

function renderPagination(data) {
  let pagination = document.querySelector("#paginationTrafficSpeed");

  if (!pagination) {
    const nav = document.createElement("nav");

    nav.innerHTML = `
<ul
id="paginationTrafficSpeed"
class="
pagination
justify-content-end
"
data-bs-theme="dark">
</ul>
`;

    document.querySelector("#modal-detail-TS .modal-body").appendChild(nav);

    pagination = document.querySelector("#paginationTrafficSpeed");
  }

  pagination.innerHTML = "";

  // PREVIOUS

  pagination.innerHTML += `
<li
class="
page-item
${data.first ? "disabled" : ""}
">

<button
class="page-link"
onclick="
changePage(
${data.number - 1}
)
">

Previous

</button>

</li>
`;

  const current = data.number;

  const total = data.totalPages;

  const pages = new Set();

  pages.add(0);

  for (let i = current - 2; i <= current + 2; i++) {
    if (i >= 0 && i < total) {
      pages.add(i);
    }
  }

  pages.add(total - 1);

  const sorted = [...pages].sort((a, b) => a - b);

  let prev = -1;

  sorted.forEach((page) => {
    if (page - prev > 1) {
      pagination.innerHTML += `

<li
class="
page-item
disabled
">

<span
class="
page-link
">

...

</span>

</li>

`;
    }

    pagination.innerHTML += `

<li
class="
page-item
${page === current ? "active" : ""}
">

<button
class="
page-link
"

onclick="
changePage(
${page}
)
"

>

${page + 1}

</button>

</li>

`;

    prev = page;
  });

  // NEXT

  pagination.innerHTML += `

<li
class="
page-item
${data.last ? "disabled" : ""}
">

<button
class="
page-link
"

onclick="
changePage(
${data.number + 1}
)
"

>

Next

</button>

</li>

`;
}

function changePage(page) {
  openDetailTrafficSpeed(
    {
      dataset: {
        id: currentTrafficId,
      },
    },
    page,
  );
}

// hàm đóng modal traffic info
arrCpModel.push(cpModalTraffic, cpModalDeleteTraffic, cpModalDetailTS);
arrCpModel.map((value, index) =>
  value.addEventListener("hide.bs.modal", () => {
    document.activeElement?.blur();
  }),
);
cpModalTraffic.addEventListener("hidden.bs.modal", () => {
  pointMake?.remove();
  formTraffic.reset();
  cpInputTrafficId.style = "display:none";
});
// hàm xử lý biểu đồ
async function getAllChartTrafficSpeed() {
  try {
    const res = await axios.get(
      "http://localhost:4080/get-chart-traffic-speed",
    );
    const result = res.data;
    // destroy chart cũ nếu có
    Chart.getChart("ChartTrafficSpeed")?.destroy();

    const labelDate =
      result[0]?.data.map((item) => {
        const date = new Date(item.createdAt);

        return date.toLocaleString("vi-VN", {
          day: "2-digit",
          month: "2-digit",
          year: "numeric",
          hour: "2-digit",
          minute: "2-digit",
        });
      }) || [];
    const labels = labelDate.slice(labelDate.length - 20, labelDate.length);
    const datasets = result.map((item, index) => {
      const dataSpeed = item.data.map((v) => v.currentSpeed);
      return {
        label: item.address,
        data: dataSpeed.slice(dataSpeed.length - 20, dataSpeed.length),
        fill: false,
        tension: 0.3,
      };
    });
    // const datasets = dataCharts.slice(
    //   dataCharts.length - 20,
    //   dataCharts.length,
    // );

    new Chart(document.getElementById("ChartTrafficSpeed"), {
      type: "line",

      data: {
        labels,
        datasets,
      },
      options: {
        responsive: true,
        scales: {
          x: {
            ticks: {
              color: "#ffffff", // chữ trên trục X

              font: {
                size: 12,
              },
            },

            grid: {
              color: "rgba(255,255,255,0.08)",
            },
          },

          y: {
            ticks: {
              color: "#ffffff", // chữ trên trục Y

              font: {
                size: 12,
              },
            },

            title: {
              display: true,
              text: "Tốc độ",

              color: "#ffffff",

              font: {
                size: 15,
              },
            },
            grid: {
              color: "rgba(255,255,255,0.08)",
            },

            beginAtZero: true,
          },
        },
        plugins: {
          legend: {
            labels: {
              color: "#ffffff", // chữ trắng
              usePointStyle: true,
              pointStyle: "circle",
            },
          },
        },
      },
    });
  } catch (err) {
    console.error("ERROR: Get all traffic speed!", err);
    return `ERROR: ${err}`;
  }
}
getAllChartTrafficSpeed();
