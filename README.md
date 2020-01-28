# Assessment

1. This application is using api from [Mocky](https://www.mocky.io/)
2. In case the application return error, head to mocky,
add this json object and click `Generate my HTTP Response`
```json
{
  "engineers": [
    {
      "id": 0,
      "name": "Bogdan"
    },
    {
      "id": 1,
      "name": "Nic"
    },
    {
      "id": 2,
      "name": "Tung"
    },
    {
      "id": 3,
      "name": "Gautam"
    },
    {
      "id": 4,
      "name": "Bala"
    },
    {
      "id": 5,
      "name": "Nazih"
    },
    {
      "id": 6,
      "name": "Huteri"
    },
    {
      "id": 7,
      "name": "Aldy"
    },
    {
      "id": 8,
      "name": "Ankur"
    },
    {
      "id": 9,
      "name": "Chinh"
    }
  ]
}
```

3. This will produce link. Get the endpoint and replace `my/com/assessments/utilities/retrofit_util.kt` line 30 with the value.

4. Run the project again.

## Project Structure

1. Kotlin / KotlinX
2. AndroidX / Jetpack
3. Retrofit
4. Custom injection. I don't think I should use [Dagger](https://dagger.dev/android.html) / [Koin](https://insert-koin.io/) since I don't have the luxury.

## References:

- [sunflower](https://github.com/android/sunflower)
- [mockito](https://developer.android.com/training/testing/unit-testing/local-unit-tests)
- [mockito-kotlin](https://github.com/nhaarman/mockito-kotlin/issues/311)
