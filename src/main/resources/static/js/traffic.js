// initialize variables (Khởi tạo biến)
let pointMake = null;
const API_KEY_TOMTOM = "dRrxO1yXAGX2V9Ee1RSxqG6qT0Gb64j3";
const API_KEY_AQI = "40e5dcd4ef8dc3c01daf8e51936235f7c3f471f9";
// querySelector(bộ chọn truy vấy): truy vấn với tới class or id của tags
const inputShowLat = document.querySelector("#inputShowLat");
const inputShowLng = document.querySelector("#inputShowLng");
const inputShowAddress = document.querySelector("#inputShowAddress");
const chartTrafficSpeed = document.querySelector("#ChartTrafficSpeed");
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
    inputShowLat.value = lat;
    inputShowLng.value = lng;
    inputShowAddress.value = result;
  } catch (err) {
    console.error("Error: get address:", err);
    return "Error";
  }
};
map.on("click", onMapClick);

// hàm xử lý biểu đồ
new Chart(chartTrafficSpeed, {
  type: "line",
  data: {
    labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],
    datasets: [
      {
        label: "# of Votes",
        data: [12, 19, 3, 5, 2, 3],
        borderWidth: 1,
      },
    ],
  },
  options: {
    scales: {
      y: {
        beginAtZero: true,
      },
    },
  },
});
