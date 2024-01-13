/*
 * Copyright 2023 Typelevel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.typelevel.otel4s.sdk.scalacheck

import org.scalacheck.Gen
import org.typelevel.otel4s.sdk.Resource
import org.typelevel.otel4s.sdk.common.InstrumentationScope

trait Gens extends org.typelevel.otel4s.scalacheck.Gens {

  val resource: Gen[Resource] =
    for {
      attributes <- Gens.attributes
      schemaUrl <- Gen.option(nonEmptyString)
    } yield Resource(attributes, schemaUrl)

  val instrumentationScope: Gen[InstrumentationScope] =
    for {
      name <- nonEmptyString
      version <- Gen.option(nonEmptyString)
      schemaUrl <- Gen.option(nonEmptyString)
      attributes <- Gens.attributes
    } yield InstrumentationScope(name, version, schemaUrl, attributes)

}

object Gens extends Gens